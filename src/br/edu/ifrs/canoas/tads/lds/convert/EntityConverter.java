package br.edu.ifrs.canoas.tads.lds.convert;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

 
@FacesConverter("entityConverter")
public class EntityConverter implements Converter {
	private Integer index = -1;
	  
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
       try {
           if (value == null || value.isEmpty()) {
               return null;
           }
           String indice = value;
           //String indice = JsfUtil.decodeId(value, Boolean.TRUE);
           return component.getAttributes().get(indice);
       } catch (NumberFormatException e) {
           return null;
       }
   }
 
   public String getAsString(FacesContext context, UIComponent component, Object value) {
       if (value == null) {
           return null;
       }
       index++;
       component.getAttributes().put(index.toString(), value);
 
       //return JsfUtil.encodeId(index, Boolean.TRUE);
       return index.toString();
   }
}

