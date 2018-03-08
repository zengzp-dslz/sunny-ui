package io.sunny.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NodeId {
    private static final int NODE_ID_LENGTH = 8;
    private static final int MACHINE_ID_LENGTH = 4;
    private static final Logger LOGGER = LoggerFactory.getLogger(NodeId.class);
    public static String ID;

    static {
        String tempId = System.getProperty("bootserver.nodeId");
        if (StringUtils.isBlank(tempId)) {
            tempId = System.getenv("BOOTSERVER_NODEID");
            if (StringUtils.isBlank(tempId)) {
                tempId = MacAddressUtil.simpleMachineId(MACHINE_ID_LENGTH);
            }
        }
        ID = StringUtils.substring(tempId, 0, NODE_ID_LENGTH);
        LOGGER.info("Current NodeId is: {}", ID);
    }

    private NodeId() {
    }
}
