package com.learning.tweety.tweetyhome.services.token;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.learning.tweety.tweetyhome.credentials.Credentials;
import com.learning.tweety.tweetyhome.securityconfig.ApplicationProperties;
import com.learning.tweety.tweetyhome.securityconfig.UnauthorizedException;
import com.learning.tweety.tweetyhome.services.common.RestClientService;
import java.security.Key;
import java.util.Base64;

@Service
public class JWTTokenService {
	//Cache token secret for this instance.
	String tokenSecret = null;
	@Autowired
	ApplicationProperties property;
	@Autowired
	RestClientService clientService;

	public void isValidToken(HttpServletRequest request) {
		String jwt = getJwtFromRequest(request);
		if (!StringUtils.hasText(jwt) || !isValidToken(jwt)) {
			throw new UnauthorizedException("Unauthorized Entry.");
		}
	}

	public void isValidUser(String token, String userName) {
		if(!userName.equals(getUser(token))) {
			throw new UnauthorizedException("Unauthorized Entry.");
		}
	}
	
	private String getUser(String token) {
		String jwt = getBearer(token);
		if (!StringUtils.hasText(jwt)) {
			throw new UnauthorizedException("Unauthorized Entry.");
		}
		return getUsername(jwt);
	}

	private String getUsername(String jwtString) {
		try {
			updateTokenSecret();
			Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(tokenSecret), 
					SignatureAlgorithm.HS256.getJcaName());
			return Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(jwtString)
					.getBody()
					.getSubject();
		} catch(Exception ex) {
			throw new UnauthorizedException("Unauthorized Entry.");
		}
	}

	private boolean isValidToken(String jwtString) {
		try {
			updateTokenSecret();
			Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(tokenSecret), 
					SignatureAlgorithm.HS256.getJcaName());
			Jwts.parserBuilder().setSigningKey(hmacKey).build().parseClaimsJws(jwtString);
			return true;
		} catch(Exception ex) {
			return false;
		}
	}

	private void updateTokenSecret() {
		if(!StringUtils.hasText(tokenSecret)) {
			Credentials creds = clientService.getCredentials(property.getJwtID());
			tokenSecret=creds.getPassWord();
		}
	}

	private String getBearer(String token) {
		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			return token.substring(7, token.length());
		}
		return null;
	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		return getBearer(token);
	}
}
