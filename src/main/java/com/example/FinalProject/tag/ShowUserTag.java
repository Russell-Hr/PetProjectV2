package com.example.FinalProject.tag;

import com.example.FinalProject.entity.User;

import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import javax.servlet.jsp.JspException;

public class ShowUserTag extends TagSupport {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int doStartTag() throws JspException {
        System.out.println("ShowUserTag#doStartTag");

        System.out.println("user ==> " + user);
        try {
            pageContext.getOut()
                    .append("<tr>")
                    .append("<td>");
            pageContext.getOut()
                    .println(user.getId());

            pageContext.getOut()
                    .append("</td>")
                    .append("<td>")
                    .append(user.getLogin())
                    .append("</td>")
                    .append("</tr>");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return SKIP_BODY;
    }

}





