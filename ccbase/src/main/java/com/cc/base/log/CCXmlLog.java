package com.cc.base.log;

import android.util.Log;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * 打印XML的Log
 */
class CCXmlLog
{

    /**
     * 打印xml
     *
     * @param tag        打印的tag
     * @param xml        需要打印的xml字符串
     * @param headString 打印的头信息
     */
    static void printXml(String tag, String xml, String headString)
    {
        if (xml != null)
        {
            xml = CCXmlLog.formatXML(xml);
            xml = headString + "\n" + xml;
        } else
        {
            xml = headString + CCLogUtils.NULL_TIPS;
        }
        //打印头信息
        CCLogUtils.printLine(tag, true);
        //打印各行数据
        String[] lines = xml.split(CCLogUtils.LINE_SEPARATOR);
        for (String line : lines)
        {
            if (!CCLogUtils.isEmpty(line))
            {
                Log.d(tag, "║ " + line);
            }
        }
        //打印底部的分隔线
        CCLogUtils.printLine(tag, false);
    }

    /**
     * 格式化xml字符串
     *
     * @param inputXML 输入的xml字符串
     * @return 格式化以后的xml
     */
    private static String formatXML(String inputXML)
    {
        try
        {
            Source xmlInput = new StreamSource(new StringReader(inputXML));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString().replaceFirst(">", ">\n");
        } catch (Exception e)
        {
            return inputXML;
        }
    }

}
