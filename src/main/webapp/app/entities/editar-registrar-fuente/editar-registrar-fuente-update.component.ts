import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEditarRegistrarFuente, EditarRegistrarFuente } from 'app/shared/model/editar-registrar-fuente.model';
import { EditarRegistrarFuenteService } from './editar-registrar-fuente.service';

@Component({
  selector: 'jhi-editar-registrar-fuente-update',
  templateUrl: './editar-registrar-fuente-update.component.html',
})
export class EditarRegistrarFuenteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codigo: [],
    descripcion: [],
    estado: [],
  });

  constructor(
    protected editarRegistrarFuenteService: EditarRegistrarFuenteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ editarRegistrarFuente }) => {
      this.updateForm(editarRegistrarFuente);
    });
  }

  updateForm(editarRegistrarFuente: IEditarRegistrarFuente): void {
    this.editForm.patchValue({
      id: editarRegistrarFuente.id,
      codigo: editarRegistrarFuente.codigo,
      descripcion: editarRegistrarFuente.descripcion,
      estado: editarRegistrarFuente.estado,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const editarRegistrarFuente = this.createFromForm();
    if (editarRegistrarFuente.id !== undefined) {
      this.subscribeToSaveResponse(this.editarRegistrarFuenteService.update(editarRegistrarFuente));
    } else {
      this.subscribeToSaveResponse(this.editarRegistrarFuenteService.create(editarRegistrarFuente));
    }
  }

  private createFromForm(): IEditarRegistrarFuente {
    return {
      ...new EditarRegistrarFuente(),
      id: this.editForm.get(['id'])!.value,
      codigo: this.editForm.get(['codigo'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value,
      estado: this.editForm.get(['estado'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEditarRegistrarFuente>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
