package org.webharvest.runtime.processors.plugins;

import com.sun.xml.internal.messaging.saaj.util.*;
import org.apache.commons.net.ftp.*;
import org.webharvest.runtime.*;
import org.webharvest.runtime.processors.*;
import org.webharvest.runtime.variables.*;
import org.webharvest.utils.*;

import java.io.*;
import java.util.*;

/**
 * Ftp Get plugin - can be used only inside ftp plugin for retrieving file from remote directory.
 */
public class FtpGetPlugin extends WebHarvestPlugin {

    public String getName() {
        return "ftp-get";
    }

    public Variable executePlugin(Scraper scraper, ScraperContext context) {
        FtpPlugin ftpPlugin = (FtpPlugin) scraper.getRunningProcessorOfType(FtpPlugin.class);
        if (ftpPlugin != null) {
            FTPClient ftpClient = ftpPlugin.getFtpClient();

            String path = CommonUtil.nvl( evaluateAttribute("path", scraper), "" );

            setProperty("Path", path);

            try {
                ByteOutputStream byteOutputStream = new ByteOutputStream();
                ftpClient.retrieveFile(path, byteOutputStream);
                byteOutputStream.close();
                int count = byteOutputStream.getCount();
                byte[] bytes = byteOutputStream.getBytes();
                if (count > 0 && bytes.length >= count) {
                    return new NodeVariable(Arrays.copyOf(bytes, count));
                } else {
                    return new NodeVariable(bytes);
                }
            } catch (IOException e) {
                throw new FtpPluginException(e);
            }
        } else {
            throw new FtpPluginException("Cannot use ftp get plugin out of ftp plugin context!");
        }
    }

    public String[] getValidAttributes() {
        return new String[] {"path"};
    }

    public String[] getRequiredAttributes() {
        return new String[] {"path"};
    }

    public boolean hasBody() {
        return false;
    }
}