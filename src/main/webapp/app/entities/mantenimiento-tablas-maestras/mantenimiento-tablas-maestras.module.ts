import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Requerimiento14SharedModule } from 'app/shared/shared.module';
import { MantenimientoTablasMaestrasComponent } from './mantenimiento-tablas-maestras.component';
import { MantenimientoTablasMaestrasDetailComponent } from './mantenimiento-tablas-maestras-detail.component';
import { MantenimientoTablasMaestrasUpdateComponent } from './mantenimiento-tablas-maestras-update.component';
import { MantenimientoTablasMaestrasDeleteDialogComponent } from './mantenimiento-tablas-maestras-delete-dialog.component';
import { mantenimientoTablasMaestrasRoute } from './mantenimiento-tablas-maestras.route';

@NgModule({
  imports: [Requerimiento14SharedModule, RouterModule.forChild(mantenimientoTablasMaestrasRoute)],
  declarations: [
    MantenimientoTablasMaestrasComponent,
    MantenimientoTablasMaestrasDetailComponent,
    MantenimientoTablasMaestrasUpdateComponent,
    MantenimientoTablasMaestrasDeleteDialogComponent,
  ],
  entryComponents: [MantenimientoTablasMaestrasDeleteDialogComponent],
})
export class Requerimiento14MantenimientoTablasMaestrasModule {}
