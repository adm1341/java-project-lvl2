package hexlet.code;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public final class CustomDifferObjectSerializer extends StdSerializer<DiffObject> {

    public CustomDifferObjectSerializer() {
        this(null);
    }

    public CustomDifferObjectSerializer(Class<DiffObject> t) {
        super(t);
    }

    @Override
    public void serialize(
            DiffObject diffObject, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        String val1String = diffObject.getVal1().isPresent() ? diffObject.getVal1().get().toString() : null;
        String val2String = diffObject.getVal2().isPresent() ? diffObject.getVal2().get().toString() : null;
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("Key", diffObject.getKey());
        jsonGenerator.writeStringField("Action", diffObject.getAction());
        jsonGenerator.writeStringField("ValueOne", val1String);
        jsonGenerator.writeStringField("ValueTwo", val2String);
        jsonGenerator.writeEndObject();
    }
}
