/*
 *  Copyright (c) 2005-2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.wso2.carbon.identity.provisioning.google;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.identity.provisioning.IdentityProvisioningConnectorFactory;

public class GoogleProvisioningConnectorFactory implements IdentityProvisioningConnectorFactory {

	private static final Log log = LogFactory
			.getLog(GoogleProvisioningConnectorFactory.class);
	
	private static Map<String, GoogleProvisioningConnector> connectorList = new HashMap<String, GoogleProvisioningConnector> ();

	@Override
	public GoogleProvisioningConnector buildConnector(
			String connectorName, boolean isEnabled, Properties configs) {
		
		// TODO Cache created objects instead of a map to avoid memory leaks
		if(!connectorList.containsKey(connectorName)) {
			connectorList.put(connectorName, new GoogleProvisioningConnector(connectorName, isEnabled, configs));
			if(log.isDebugEnabled()) {
				log.debug("Created new connector : " + connectorName + " of type : " + GoogleProvisioningConnector.class.toString());
			}
		}
		return connectorList.get(connectorName);
	}
	
	@Override
	public GoogleProvisioningConnector getConnector(
			String connectorName) {
		if(connectorName != null && connectorList.containsKey(connectorName)) {
			return connectorList.get(connectorName);
		}
		return null;
	}

	@Override
	public String getConnectorType() {
		return GoogleProvisioningConnector.class.getName();
	}
}