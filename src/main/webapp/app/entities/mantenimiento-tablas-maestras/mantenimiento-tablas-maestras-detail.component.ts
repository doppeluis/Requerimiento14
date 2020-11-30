import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMantenimientoTablasMaestras } from 'app/shared/model/mantenimiento-tablas-maestras.model';

@Component({
  selector: 'jhi-mantenimiento-tablas-maestras-detail',
  templateUrl: './mantenimiento-tablas-maestras-detail.component.html',
})
export class MantenimientoTablasMaestrasDetailComponent implements OnInit {
  mantenimientoTablasMaestras: IMantenimientoTablasMaestras | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(
      ({ mantenimientoTablasMaestras }) => (this.mantenimientoTablasMaestras = mantenimientoTablasMaestras)
    );
  }

  previousState(): void {
    window.history.back();
  }
}
