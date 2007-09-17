/*  Copyright (c) 2006-2007, Vladimir Nikic
    All rights reserved.

    Redistribution and use of this software in source and binary forms,
    with or without modification, are permitted provided that the following
    conditions are met:

    * Redistributions of source code must retain the above
      copyright notice, this list of conditions and the
      following disclaimer.

    * Redistributions in binary form must reproduce the above
      copyright notice, this list of conditions and the
      following disclaimer in the documentation and/or other
      materials provided with the distribution.

    * The name of Web-Harvest may not be used to endorse or promote
      products derived from this software without specific prior
      written permission.

    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
    AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
    IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
    ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
    LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
    CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
    SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
    INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
    CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
    POSSIBILITY OF SUCH DAMAGE.

    You can contact Vladimir Nikic by sending e-mail to
    nikic_vladimir@yahoo.com. Please include the word "Web-Harvest" in the
    subject line.
*/
package org.webharvest.utils;

import org.webharvest.exception.BaseException;
import org.webharvest.exception.ScraperXPathException;
import org.webharvest.runtime.variables.AbstractVariable;
import org.webharvest.runtime.variables.NodeVariable;
import org.webharvest.runtime.templaters.BaseTemplater;
import org.webharvest.runtime.RuntimeConfig;
import org.webharvest.runtime.Scraper;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.saxon.trans.XPathException;

/**
 * Collection of useful constants and functions that are available in each
 * scraper context. 
 */
public class SystemUtilities {

    public static final AbstractVariable lf = new NodeVariable("\n");
    public static final AbstractVariable tab = new NodeVariable("\t");
    public static final AbstractVariable cr = new NodeVariable("\r");
    public static final AbstractVariable space = new NodeVariable(" ");
    public static final AbstractVariable quot = new NodeVariable("\"");
    public static final AbstractVariable apos = new NodeVariable("\'");
    public static final AbstractVariable backspace = new NodeVariable("\b");
    
    private Scraper scraper;

    public SystemUtilities(Scraper scraper) {
        this.scraper = scraper;
    }

    public void setScraper(Scraper scraper) {
        this.scraper = scraper;
    }

    /**
     * @param varName
     * @return True if scraper's context contain not-null variable with specified name.
     */
    public boolean isVariableDefined(String varName) {
        return scraper.getContext().get(varName) != null;
    }

    /**
	 * Returns formatted date/time for specified format string.
	 *   
	 * @param format
	 */
	public String datetime(Object format) {
        if (format != null) {
            SimpleDateFormat formatter = new SimpleDateFormat(format.toString());
            return formatter.format( new Date() );
        }

        throw new BaseException("Cannot return datetime for null format!");
    }

    /**
     * Returns current date formated as "yyyyMMdd".
     */
    public String date() {
		return datetime("yyyyMMdd");
	}

    /**
     *
     */
    public String time() {
		return datetime("HHmmss");
	}
	
    /**
     * Escapes XML string - special characters: &'"<> are
     * replaced with XML escape sequences: &amp; &apos; &quot; &lt; &gt;
     */
    public String escapeXml(Object s) {
        if (s != null) {
            return CommonUtil.escapeXml(s.toString());
        }

        throw new BaseException("Cannot escape XML for null argumant!");
    }
    
    /**
     * Calculates full URL for specified page URL and link
     * which could be full, absolute or relative like there can 
     * be found in A or IMG tags. 
     */
    public String fullUrl(Object pageUrl, Object link) {
        if (pageUrl != null && link != null) {
            return CommonUtil.fullUrl(pageUrl.toString(), link.toString());
        }

        throw new BaseException("Cannot make full url for null argumants!");
    }

    /**
     * Evaluates XPath expression on specified XML
     * @param expression
     * @param xml
     */
    public AbstractVariable xpath(Object expression, Object xml) {
        if (expression == null) {
            throw new ScraperXPathException("XPath expression is null!");
        }

        if (xml == null) {
            throw new ScraperXPathException("XML value is null!");
        }

        try {
            return XmlUtil.evaluateXPath(expression.toString(), xml.toString(), scraper.getRuntimeConfig());
        } catch (XPathException e) {
            throw new ScraperXPathException("Error parsing XPath expression (XPath = [" + expression + "])!", e);
        }
    }

}