import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMantenimientoTablasMaestras } from 'app/shared/model/mantenimiento-tablas-maestras.model';
import { MantenimientoTablasMaestrasService } from './mantenimiento-tablas-maestras.service';

@Component({
  templateUrl: './mantenimiento-tablas-maestras-delete-dialog.component.html',
})
export class MantenimientoTablasMaestrasDeleteDialogComponent {
  mantenimientoTablasMaestras?: IMantenimientoTablasMaestras;

  constructor(
    protected mantenimientoTablasMaestrasService: MantenimientoTablasMaestrasService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mantenimientoTablasMaestrasService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mantenimientoTablasMaestrasListModification');
      this.activeModal.close();
    });
  }
}
