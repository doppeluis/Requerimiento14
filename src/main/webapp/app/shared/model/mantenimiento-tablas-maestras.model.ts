import { IEditarRegistrarFuente } from 'app/shared/model/editar-registrar-fuente.model';

export interface IMantenimientoTablasMaestras {
  id?: number;
  tecnicaEvaluativa?: string;
  operaciones?: IEditarRegistrarFuente;
}

export class MantenimientoTablasMaestras implements IMantenimientoTablasMaestras {
  constructor(public id?: number, public tecnicaEvaluativa?: string, public operaciones?: IEditarRegistrarFuente) {}
}
