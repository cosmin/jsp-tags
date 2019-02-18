package com.offbytwo.jsp;

import com.offbytwo.ulid.ULID;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.UUID;

public class UUIDasULIDTag extends SimpleTagSupport {
    private UUID value;

    public void setValue(UUID value) {
        this.value = value;
    }

    public void doTag() throws JspException, IOException {
        if (value != null) {
            JspWriter out = getJspContext().getOut();
            out.println(ULID.fromUUID(value).toString());
        }
    }
}
