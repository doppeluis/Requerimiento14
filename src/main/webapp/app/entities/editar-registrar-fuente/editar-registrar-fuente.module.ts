import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Requerimiento14SharedModule } from 'app/shared/shared.module';
import { EditarRegistrarFuenteComponent } from './editar-registrar-fuente.component';
import { EditarRegistrarFuenteDetailComponent } from './editar-registrar-fuente-detail.component';
import { EditarRegistrarFuenteUpdateComponent } from './editar-registrar-fuente-update.component';
import { EditarRegistrarFuenteDeleteDialogComponent } from './editar-registrar-fuente-delete-dialog.component';
import { editarRegistrarFuenteRoute } from './editar-registrar-fuente.route';

@NgModule({
  imports: [Requerimiento14SharedModule, RouterModule.forChild(editarRegistrarFuenteRoute)],
  declarations: [
    EditarRegistrarFuenteComponent,
    EditarRegistrarFuenteDetailComponent,
    EditarRegistrarFuenteUpdateComponent,
    EditarRegistrarFuenteDeleteDialogComponent,
  ],
  entryComponents: [EditarRegistrarFuenteDeleteDialogComponent],
})
export class Requerimiento14EditarRegistrarFuenteModule {}
