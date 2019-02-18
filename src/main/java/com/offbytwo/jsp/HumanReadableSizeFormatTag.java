package com.offbytwo.jsp;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.text.DecimalFormat;

public class HumanReadableSizeFormatTag extends SimpleTagSupport {
    private Number value;
    private String suffix = "B";

    public void setValue(Number value) {
        this.value = value;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if (value == null) {
            out.write("N/A");
        } else {
            out.write(readableFileSize(value, suffix));
        }
    }

    public static String readableFileSize(Number size, String suffix) {
        if (size.longValue() <= 0) return "0";
        final String[] units = new String[]{"", "K", "M", "G", "T"};
        int digitGroups = (int) (Math.log10(size.longValue()) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size.longValue() / Math.pow(1024, digitGroups)) + " " + units[digitGroups] + suffix;
    }
}
