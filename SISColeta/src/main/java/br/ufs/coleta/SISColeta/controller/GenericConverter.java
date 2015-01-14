package br.ufs.coleta.SISColeta.controller;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufs.coleta.SISColeta.entities.GenericEntity;



@FacesConverter("generic")
public class GenericConverter implements Converter,Serializable{
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null) {
            return this.getAttributesFrom(component).get(value);
        }
        return null;
    }

    public String getAsString(FacesContext ctx, UIComponent component, Object value) {

        if ((value != null
                && !"".equals(value))&&(!value.equals("null"))) {

            GenericEntity entity = (GenericEntity) value;

            // adiciona item como atributo do componente
            this.addAttribute(component, entity);

            Number codigo = entity.getId();
            if (codigo != null) {
                return String.valueOf(codigo);
            }
        }

        return (String) value;
    }

    protected void addAttribute(UIComponent component, GenericEntity o) {
        String key = o.getId().toString();
        this.getAttributesFrom(component).put(key, o);
    }

    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }

}