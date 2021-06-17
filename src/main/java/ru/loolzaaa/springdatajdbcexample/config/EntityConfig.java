package ru.loolzaaa.springdatajdbcexample.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import ru.loolzaaa.springdatajdbcexample.model.EntitySettings;

import java.util.List;

@Configuration
public class EntityConfig extends AbstractJdbcConfiguration {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public JdbcCustomConversions jdbcCustomConversions() {
        return new JdbcCustomConversions(List.of(
                StringToEntitySettingsConverter.INSTANCE,
                EntitySettingsToStringConverter.INSTANCE
        ));
    }

    @ReadingConverter // This annotation is optional!? NEED HELP!
    enum StringToEntitySettingsConverter implements Converter<String, EntitySettings> {

        INSTANCE;

        @SneakyThrows
        @Override
        public EntitySettings convert(String source) {
            System.out.println(String.format("I'm in %s converter", getClass().getSimpleName()));
            return objectMapper.readValue(source, EntitySettings.class);
        }
    }

    @WritingConverter // This annotation is optional!? NEED HELP!
    enum EntitySettingsToStringConverter implements Converter<EntitySettings, String> {

        INSTANCE;

        @SneakyThrows
        @Override
        public String convert(EntitySettings entitySettings) {
            System.out.println(String.format("I'm in %s converter", getClass().getSimpleName()));
            return objectMapper.writeValueAsString(entitySettings);
        }
    }
}
