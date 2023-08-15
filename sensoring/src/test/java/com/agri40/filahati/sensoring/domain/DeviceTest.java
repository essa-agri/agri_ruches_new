package com.agri40.filahati.sensoring.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.agri40.filahati.sensoring.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeviceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Device.class);
        Device device1 = new Device();
        device1.setId("id1");
        Device device2 = new Device();
        device2.setId(device1.getId());
        assertThat(device1).isEqualTo(device2);
        device2.setId("id2");
        assertThat(device1).isNotEqualTo(device2);
        device1.setId(null);
        assertThat(device1).isNotEqualTo(device2);
    }
}
