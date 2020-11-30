package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class MantenimientoTablasMaestrasTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MantenimientoTablasMaestras.class);
        MantenimientoTablasMaestras mantenimientoTablasMaestras1 = new MantenimientoTablasMaestras();
        mantenimientoTablasMaestras1.setId(1L);
        MantenimientoTablasMaestras mantenimientoTablasMaestras2 = new MantenimientoTablasMaestras();
        mantenimientoTablasMaestras2.setId(mantenimientoTablasMaestras1.getId());
        assertThat(mantenimientoTablasMaestras1).isEqualTo(mantenimientoTablasMaestras2);
        mantenimientoTablasMaestras2.setId(2L);
        assertThat(mantenimientoTablasMaestras1).isNotEqualTo(mantenimientoTablasMaestras2);
        mantenimientoTablasMaestras1.setId(null);
        assertThat(mantenimientoTablasMaestras1).isNotEqualTo(mantenimientoTablasMaestras2);
    }
}
