//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.9-03/31/2009 04:14 PM(snajper)-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.11.28 at 05:46:04 ���� CST 
//


package com.ganglia.watcher.xml.bean;

import java.lang.reflect.Field;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}EXTRA_DATA"/>
 *       &lt;/sequence>
 *       &lt;attribute name="DMAX" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="NAME" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="SLOPE" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="SOURCE" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="TMAX" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="TN" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="TYPE" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="UNITS" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="VAL" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "extradata"
})
@XmlRootElement(name = "METRIC")
public class METRIC {

    @XmlElement(name = "EXTRA_DATA", required = true)
    protected EXTRADATA extradata;
    @XmlAttribute(name = "DMAX", required = true)
    protected BigInteger dmax;
    @XmlAttribute(name = "NAME", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String name;
    @XmlAttribute(name = "SLOPE", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String slope;
    @XmlAttribute(name = "SOURCE", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String source;
    @XmlAttribute(name = "TMAX", required = true)
    protected BigInteger tmax;
    @XmlAttribute(name = "TN", required = true)
    protected BigInteger tn;
    @XmlAttribute(name = "TYPE", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String type;
    @XmlAttribute(name = "UNITS", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String units;
    @XmlAttribute(name = "VAL", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String val;

    /**
     * Gets the value of the extradata property.
     * 
     * @return
     *     possible object is
     *     {@link EXTRADATA }
     *     
     */
    public EXTRADATA getEXTRADATA() {
        return extradata;
    }

    /**
     * Sets the value of the extradata property.
     * 
     * @param value
     *     allowed object is
     *     {@link EXTRADATA }
     *     
     */
    public void setEXTRADATA(EXTRADATA value) {
        this.extradata = value;
    }

    /**
     * Gets the value of the dmax property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDMAX() {
        return dmax;
    }

    /**
     * Sets the value of the dmax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDMAX(BigInteger value) {
        this.dmax = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNAME() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNAME(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the slope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSLOPE() {
        return slope;
    }

    /**
     * Sets the value of the slope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSLOPE(String value) {
        this.slope = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSOURCE() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSOURCE(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the tmax property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTMAX() {
        return tmax;
    }

    /**
     * Sets the value of the tmax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTMAX(BigInteger value) {
        this.tmax = value;
    }

    /**
     * Gets the value of the tn property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTN() {
        return tn;
    }

    /**
     * Sets the value of the tn property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTN(BigInteger value) {
        this.tn = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTYPE() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTYPE(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the units property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUNITS() {
        return units;
    }

    /**
     * Sets the value of the units property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUNITS(String value) {
        this.units = value;
    }

    /**
     * Gets the value of the val property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVAL() {
        return val;
    }

    /**
     * Sets the value of the val property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVAL(String value) {
        this.val = value;
    }

	public String toString(){
		StringBuffer sb = new StringBuffer(128);
		try {
			sb.append(getClass().getName()+"\n{");
			for(Field field : getClass().getDeclaredFields()){
				sb.append("\n"+field.getName() + ":" +field.get(this).toString()+",\n ");
			}
			sb.append("}");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}
