import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEditarRegistrarFuente } from 'app/shared/model/editar-registrar-fuente.model';

@Component({
  selector: 'jhi-editar-registrar-fuente-detail',
  templateUrl: './editar-registrar-fuente-detail.component.html',
})
export class EditarRegistrarFuenteDetailComponent implements OnInit {
  editarRegistrarFuente: IEditarRegistrarFuente | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ editarRegistrarFuente }) => (this.editarRegistrarFuente = editarRegistrarFuente));
  }

  previousState(): void {
    window.history.back();
  }
}
