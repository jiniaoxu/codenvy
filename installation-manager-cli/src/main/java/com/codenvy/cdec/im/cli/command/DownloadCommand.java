/*
 * CODENVY CONFIDENTIAL
 * __________________
 *
 *  [2012] - [2014] Codenvy, S.A.
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.cdec.im.cli.command;

import com.codenvy.cdec.im.service.response.Response;

import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.apache.karaf.shell.commands.Option;

import org.restlet.resource.ResourceException;

import com.codenvy.cdec.InstallationManagerService;
import com.codenvy.cdec.RestletClientFactory;
import com.codenvy.cdec.utils.Commons;
import com.codenvy.cli.command.builtin.AbsCommand;

import static com.codenvy.cdec.im.cli.command.MessageHelper.*;

/**
 * TODO check
 * Parameters and execution of 'cdec:download' command.
 *
 * @author Dmytro Nochevnov
 */
@Command(scope = "cdec", name = "download", description = "Download artifacts.")
public class DownloadCommand extends AbsCommand {

    InstallationManagerService installationManagerProxy;

    @Argument(index = 0, name = "artifact", description = "The name of artifact.", required = false, multiValued = false)
    String artifactName;

    @Argument(index = 1, name = "version", description = "The name of version of artifact.", required = false, multiValued = false)
    String version;

    @Option(name = "-a", aliases = "--all", description = "Download all available updates.", required = false, multiValued = false)
    boolean downloadAll;

    /**
     * Download artifacts.
     */
    protected Object doExecute() throws Exception {
        init();
        
        installationManagerProxy = RestletClientFactory.getServiceProxy(InstallationManagerService.class);

        try {            
            if (artifactName != null && version != null) {
                String response = installationManagerProxy.download(artifactName, version);  
                if (response == null) {
                    MessageHelper.printlnRed(INCOMPLETE_RESPONSE);
                    return null;
                }
                
                MessageHelper.printlnGreen(Commons.getPrettyPrintingJson(response));                
                return null;
            }
                
            if (artifactName != null && version == null) {
                String response = installationManagerProxy.downloadUpdate(artifactName);
                if (response == null) {
                    MessageHelper.printlnRed(INCOMPLETE_RESPONSE);
                    return null;
                }
                
                MessageHelper.printlnGreen(Commons.getPrettyPrintingJson(response));                
                return null;
            } 
            
            if (artifactName == null && version == null && downloadAll) {                
                String response = installationManagerProxy.downloadUpdates();
                if (response == null) {
                    MessageHelper.printlnRed(INCOMPLETE_RESPONSE);
                    return null;
                }
                MessageHelper.printlnGreen(Commons.getPrettyPrintingJson(response));                
                return null;
            } 
            
            MessageHelper.printlnRed(MISLEADING_ARGUMENTS);
            
        } catch (ResourceException re) {
            MessageHelper.println(re);
        }

        return null;
    }
}