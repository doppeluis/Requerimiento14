package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class EditarRegistrarFuenteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EditarRegistrarFuente.class);
        EditarRegistrarFuente editarRegistrarFuente1 = new EditarRegistrarFuente();
        editarRegistrarFuente1.setId(1L);
        EditarRegistrarFuente editarRegistrarFuente2 = new EditarRegistrarFuente();
        editarRegistrarFuente2.setId(editarRegistrarFuente1.getId());
        assertThat(editarRegistrarFuente1).isEqualTo(editarRegistrarFuente2);
        editarRegistrarFuente2.setId(2L);
        assertThat(editarRegistrarFuente1).isNotEqualTo(editarRegistrarFuente2);
        editarRegistrarFuente1.setId(null);
        assertThat(editarRegistrarFuente1).isNotEqualTo(editarRegistrarFuente2);
    }
}
