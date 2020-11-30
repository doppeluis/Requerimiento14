import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMantenimientoTablasMaestras, MantenimientoTablasMaestras } from 'app/shared/model/mantenimiento-tablas-maestras.model';
import { MantenimientoTablasMaestrasService } from './mantenimiento-tablas-maestras.service';
import { IEditarRegistrarFuente } from 'app/shared/model/editar-registrar-fuente.model';
import { EditarRegistrarFuenteService } from 'app/entities/editar-registrar-fuente/editar-registrar-fuente.service';

@Component({
  selector: 'jhi-mantenimiento-tablas-maestras-update',
  templateUrl: './mantenimiento-tablas-maestras-update.component.html',
})
export class MantenimientoTablasMaestrasUpdateComponent implements OnInit {
  isSaving = false;
  editarregistrarfuentes: IEditarRegistrarFuente[] = [];

  editForm = this.fb.group({
    id: [],
    tecnicaEvaluativa: [],
    operaciones: [],
  });

  constructor(
    protected mantenimientoTablasMaestrasService: MantenimientoTablasMaestrasService,
    protected editarRegistrarFuenteService: EditarRegistrarFuenteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mantenimientoTablasMaestras }) => {
      this.updateForm(mantenimientoTablasMaestras);

      this.editarRegistrarFuenteService
        .query()
        .subscribe((res: HttpResponse<IEditarRegistrarFuente[]>) => (this.editarregistrarfuentes = res.body || []));
    });
  }

  updateForm(mantenimientoTablasMaestras: IMantenimientoTablasMaestras): void {
    this.editForm.patchValue({
      id: mantenimientoTablasMaestras.id,
      tecnicaEvaluativa: mantenimientoTablasMaestras.tecnicaEvaluativa,
      operaciones: mantenimientoTablasMaestras.operaciones,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mantenimientoTablasMaestras = this.createFromForm();
    if (mantenimientoTablasMaestras.id !== undefined) {
      this.subscribeToSaveResponse(this.mantenimientoTablasMaestrasService.update(mantenimientoTablasMaestras));
    } else {
      this.subscribeToSaveResponse(this.mantenimientoTablasMaestrasService.create(mantenimientoTablasMaestras));
    }
  }

  private createFromForm(): IMantenimientoTablasMaestras {
    return {
      ...new MantenimientoTablasMaestras(),
      id: this.editForm.get(['id'])!.value,
      tecnicaEvaluativa: this.editForm.get(['tecnicaEvaluativa'])!.value,
      operaciones: this.editForm.get(['operaciones'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMantenimientoTablasMaestras>>): void {
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

  trackById(index: number, item: IEditarRegistrarFuente): any {
    return item.id;
  }
}
