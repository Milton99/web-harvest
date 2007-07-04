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
package org.webharvest.runtime.processors;

import net.sf.saxon.Configuration;
import net.sf.saxon.om.Item;
import net.sf.saxon.om.SequenceIterator;
import net.sf.saxon.om.NamespaceResolver;
import net.sf.saxon.query.DynamicQueryContext;
import net.sf.saxon.query.StaticQueryContext;
import net.sf.saxon.query.XQueryExpression;
import net.sf.saxon.trans.XPathException;
import org.webharvest.definition.XPathDef;
import org.webharvest.exception.ScraperXPathException;
import org.webharvest.runtime.Scraper;
import org.webharvest.runtime.ScraperContext;
import org.webharvest.runtime.RuntimeConfig;
import org.webharvest.runtime.templaters.BaseTemplater;
import org.webharvest.runtime.variables.IVariable;
import org.webharvest.runtime.variables.ListVariable;
import org.webharvest.runtime.variables.NodeVariable;
import org.webharvest.utils.CommonUtil;
import org.webharvest.utils.XmlUtil;
import org.xml.sax.*;

import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import java.io.StringReader;
import java.io.IOException;
import java.util.Iterator;

/**
 * XQuery processor.
 */
public class XPathProcessor extends BaseProcessor {

    private XPathDef xpathDef;

    public XPathProcessor(XPathDef xpathDef) {
        super(xpathDef);
        this.xpathDef = xpathDef;
    }

    public IVariable execute(Scraper scraper, ScraperContext context) {
        IVariable xml = getBodyTextContent(xpathDef, scraper, context);
        String expression = BaseTemplater.execute( xpathDef.getExpression(), scraper.getScriptEngine() );
        this.setProperty("Expression", expression);

        try {
            RuntimeConfig runtimeConfig = scraper.getRuntimeConfig();
            return XmlUtil.evaluateXPath(expression, xml.toString(), runtimeConfig);
        } catch (XPathException e) {
        	e.printStackTrace();
            throw new ScraperXPathException("Error parsing XPath expression (XPath = [" + expression + "])!", e);
        }
    }

    public static void main(String[] args) throws SAXException, IOException {
        XMLReader parser = org.xml.sax.helpers.XMLReaderFactory.createXMLReader();
        parser.setFeature("http://xml.org/sax/features/namespaces", false);
        String xml = "<mama><a:td>ewfewfwe</a:td><td>ewf324324e</td></mama>";
        parser.parse(new InputSource(new StringReader(xml)));
    }

}