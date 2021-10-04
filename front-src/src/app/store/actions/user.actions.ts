export class AddUserRole {
    static readonly type = '[Roles] add';
    constructor(public payload: string) {}
}
