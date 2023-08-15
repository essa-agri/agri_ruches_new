export interface IRuche {
  id?: string;
  rucheName?: string;
  indentifiant?: string;
  description?: string;
  rucherId?: string | null;
  deviceId?: string | null;
}

export const defaultValue: Readonly<IRuche> = {};
