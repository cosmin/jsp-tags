package com.offbytwo.jsp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.NullNode;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class JsonifyTag extends SimpleTagSupport {
    protected Object value;
    protected boolean pretty = false;
    protected ObjectMapper objectMapper;

    public JsonifyTag() {
        objectMapper = new ObjectMapper();
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setPretty(boolean pretty) {
        this.pretty = pretty;
    }

    public void doTag() throws JspException, IOException {
        if (value != null && !(value instanceof NullNode)) {
            JspWriter out = getJspContext().getOut();
            ObjectWriter writer;
            if (pretty) {
                writer = objectMapper.writerWithDefaultPrettyPrinter();
            } else {
                writer = objectMapper.writer();
            }

            out.println(writer.writeValueAsString(value));
        }
    }

}
