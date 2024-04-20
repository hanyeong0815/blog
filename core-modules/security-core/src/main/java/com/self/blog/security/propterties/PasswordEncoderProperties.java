package com.self.music.passwordEncoder.propterties;

import com.self.music.passwordEncoder.type.EncoderType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("app.security.password")
@ConfigurationPropertiesBinding
public record PasswordEncoderProperties(
        EncoderType defaultEncoder,
        @NestedConfigurationProperty
        BcryptProperties bcrypt,
        @NestedConfigurationProperty
        ScryptProperties scrypt,
        @NestedConfigurationProperty
        Pbkdf2Properties pbkdf2
) {
        public PasswordEncoderProperties {
                if (defaultEncoder == null) defaultEncoder = EncoderType.BCRYPT;
                if (bcrypt == null) bcrypt = new BcryptProperties(null);
                if (scrypt == null) scrypt = new ScryptProperties(null, null, null, null, null);
                if (pbkdf2 == null) pbkdf2 = new Pbkdf2Properties(null, null, null, null);
        }
}
