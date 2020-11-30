import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEditarRegistrarFuente } from 'app/shared/model/editar-registrar-fuente.model';
import { EditarRegistrarFuenteService } from './editar-registrar-fuente.service';

@Component({
  templateUrl: './editar-registrar-fuente-delete-dialog.component.html',
})
export class EditarRegistrarFuenteDeleteDialogComponent {
  editarRegistrarFuente?: IEditarRegistrarFuente;

  constructor(
    protected editarRegistrarFuenteService: EditarRegistrarFuenteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.editarRegistrarFuenteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('editarRegistrarFuenteListModification');
      this.activeModal.close();
    });
  }
}
