package co.bitcarpentry.codedecipher.view.converter;

import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=URL.class)
public class URLConverter implements Converter<URL> {

    @Override
    public URL getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) {
            return null;
        }

        try {
            return new URL(value);
        }
        catch (MalformedURLException e) {
            throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to URL", value)), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, URL value) {
        if (value == null) {
            return "";
        }

        return value.toString();
    }

}