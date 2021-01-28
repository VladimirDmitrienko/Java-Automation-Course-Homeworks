package operation;

import db.DB_Reader;
import db.DB_Writer;

public abstract class Operation implements Executable {
    public DB_Writer db_writer = new DB_Writer();
    public DB_Reader db_reader = new DB_Reader();

    abstract void showSuccessfulOperationMessage();
}