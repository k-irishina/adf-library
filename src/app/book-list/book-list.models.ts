export interface Book {
    bookId: number;
    bookName: string;
    isbn: string;
    year: string;
    currentReader: Reader;
}

export interface Reader {
    readerId: number;
    firstName: string;
    lastName: string;
    phoneNumber: string;
}