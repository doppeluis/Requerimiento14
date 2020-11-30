import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'editar-registrar-fuente',
        loadChildren: () =>
          import('./editar-registrar-fuente/editar-registrar-fuente.module').then(m => m.Requerimiento14EditarRegistrarFuenteModule),
      },
      {
        path: 'mantenimiento-tablas-maestras',
        loadChildren: () =>
          import('./mantenimiento-tablas-maestras/mantenimiento-tablas-maestras.module').then(
            m => m.Requerimiento14MantenimientoTablasMaestrasModule
          ),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class Requerimiento14EntityModule {}
