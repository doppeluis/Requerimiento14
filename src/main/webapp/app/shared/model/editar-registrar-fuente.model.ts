export interface IEditarRegistrarFuente {
  id?: number;
  codigo?: number;
  descripcion?: string;
  estado?: string;
}

export class EditarRegistrarFuente implements IEditarRegistrarFuente {
  constructor(public id?: number, public codigo?: number, public descripcion?: string, public estado?: string) {}
}
