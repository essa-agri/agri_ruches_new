export interface IRucher {
  id?: string;
  rucheName?: string;
  adresse?: string;
  description?: string;
  userId?: string;
  listRuches?: string | null;
}

export const defaultValue: Readonly<IRucher> = {};
