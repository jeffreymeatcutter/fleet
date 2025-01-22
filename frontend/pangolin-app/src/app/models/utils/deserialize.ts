export function deserialize<T>(cls: new (data: any) => T, data: any): T {
  return new cls(data);
}
