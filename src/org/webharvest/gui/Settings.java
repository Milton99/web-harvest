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
package org.webharvest.gui;

/**
 * @author: Vladimir Nikic
 * Date: Apr 27, 2007
 */
public class Settings {

    private String workingPath = System.getProperty("java.io.tmpdir");
    private boolean isProxyEnabled;
    private String proxyServer;
    private int proxyPort = -1;
    private boolean isProxyAuthEnabled;
    private String proxyUserename;
    private String proxyPassword;

    // specify if processors are located in source while configuration is running
    private boolean isDynamicConfigLocate = true;

    public boolean isProxyAuthEnabled() {
        return isProxyAuthEnabled;
    }

    public void setProxyAuthEnabled(boolean proxyAuthEnabled) {
        isProxyAuthEnabled = proxyAuthEnabled;
    }

    public boolean isProxyEnabled() {
        return isProxyEnabled;
    }

    public void setProxyEnabled(boolean proxyEnabled) {
        isProxyEnabled = proxyEnabled;
    }

    public String getProxyPassword() {
        return proxyPassword;
    }

    public void setProxyPassword(String proxyPassword) {
        this.proxyPassword = proxyPassword;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyServer() {
        return proxyServer;
    }

    public void setProxyServer(String proxyServer) {
        this.proxyServer = proxyServer;
    }

    public String getProxyUserename() {
        return proxyUserename;
    }

    public void setProxyUserename(String proxyUserename) {
        this.proxyUserename = proxyUserename;
    }

    public String getWorkingPath() {
        return workingPath;
    }

    public void setWorkingPath(String workingPath) {
        this.workingPath = workingPath;
    }

    public boolean isDynamicConfigLocate() {
        return this.isDynamicConfigLocate;
    }

    public void setDynamicConfigLocate(boolean dynamicConfigLocate) {
        isDynamicConfigLocate = dynamicConfigLocate;
    }
    
}