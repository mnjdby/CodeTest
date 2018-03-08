package com.fullerton.olp.settings.configuration;

import java.io.IOException;
import java.io.Writer;

import com.sun.xml.bind.marshaller.MinimumEscapeHandler;

public class CDataAwareUtfEncodedXmlCharacterEscapeHandler implements com.sun.xml.bind.marshaller.CharacterEscapeHandler {

    private static final char[] cDataPrefix = "<![CDATA[".toCharArray();
    private static final char[] cDataSuffix = "]]>".toCharArray();

    public static final CDataAwareUtfEncodedXmlCharacterEscapeHandler instance = new CDataAwareUtfEncodedXmlCharacterEscapeHandler();

    public CDataAwareUtfEncodedXmlCharacterEscapeHandler() {
    }

    @Override
    public void escape(char[] ch, int start, int length, boolean isAttVal, Writer out) throws IOException {
        boolean isCData = length > cDataPrefix.length + cDataSuffix.length;
        if (isCData) {
            for (int i = 0, j = start; i < cDataPrefix.length; ++i, ++j) {
                if (cDataPrefix[i] != ch[j]) {
                    isCData = false;
                    break;
                }
            }
            if (isCData) {
                for (int i = cDataSuffix.length - 1, j = start + length - 1; i >= 0; --i, --j) {
                    if (cDataSuffix[i] != ch[j]) {
                        isCData = false;
                        break;
                    }
                }
            }
        }
        if (isCData) {
            out.write(ch, start, length);
        } else {
            MinimumEscapeHandler.theInstance.escape(ch, start, length, isAttVal, out);
        }
    }
}