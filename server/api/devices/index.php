<?php
  include '../db.php';
	header("Content-Type: application/json");


  $db = get_db();
  $query = <<<SQL
SELECT masterDevice.deviceId AS id,
       masterDevice.userName AS name,
       masterDevice.interconnect AS interconnect,
       zigbeeDevice.deviceType AS deviceTypeId
FROM masterDevice, zigbeeDevice
WHERE zigbeeDevice.masterId = masterDevice.deviceId
UNION
SELECT masterDevice.deviceId AS id,
       masterDevice.userName AS name,
       masterDevice.interconnect AS interconnect,
       lutronDevice.deviceClass AS deviceClassId
FROM masterDevice, lutronDevice
WHERE lutronDevice.masterId = masterDevice.deviceId;
SQL;

  $result = $db->query($query);
  if (!$result) {
          echo json_encode(["error" => "Cannot execute query."]);
          return;
  }

        $data = array();
        while($row = $result->fetchArray(SQLITE3_ASSOC)){
                $attr_query = <<<SQL
SELECT zigbeeDeviceState.attributeId AS attributeTypeId,
       zigbeeDeviceState.value_get AS value,
	   zigbeeAttribute.description AS description,
	   zigbeeAttribute.dataType AS dataType
FROM zigbeeDevice, zigbeeDeviceState, zigbeeAttribute
WHERE zigbeeDevice.globalId = zigbeeDeviceState.globalId
      AND zigbeeDevice.masterId = '{$row['id']}'
	  AND zigbeeAttribute.attributeId = zigbeeDeviceState.attributeId
UNION ALL
SELECT lutronDeviceState.attributeId AS attributeTypeId,
       lutronDeviceState.value_get AS value,
	   lutronAttribute.description AS description,
	   lutronAttribute.dataType AS dataType
FROM lutronDevice, lutronDeviceState, lutronAttribute
WHERE lutronDevice.lNodeId = lutronDeviceState.lNodeId
      AND lutronDevice.masterId = '{$row['id']}'
	  AND lutronAttribute.attributeId = lutronDeviceState.attributeId;
SQL;

                $attr_result = $db->query($attr_query);

                $attrs = array();
                while($attr = $attr_result->fetchArray(SQLITE3_ASSOC)) {
                  $attrs[] = $attr;
                }
                $row['attributes'] = $attrs;
                $data[] = $row;
        }
        echo json_encode($data);
        $db->close()
?>
