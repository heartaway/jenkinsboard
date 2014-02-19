package com.taobao.tae.ewall.project;

import java.util.HashMap;
import java.util.Map;

public abstract class DefaultProjectGridImpl extends ProjectGrid {
    /**
     * Actual data store is a sparse map.
     */
    protected final Map<Integer/*row*/, Map<Integer/*height*/, ProjectDO>> data = new HashMap<Integer, Map<Integer, ProjectDO>>();

    /**
     * Dimension of the {@link #data}
     */
    private int rows, cols;

    public void set(int row, int col, ProjectDO p) {
        Map<Integer, ProjectDO> c = data.get(row);
        if (c == null) {
            c = new HashMap<Integer, ProjectDO>();
            data.put(row, c);
        }
        c.put(col, p);

        rows = Math.max(rows, row + 1);
        cols = Math.max(cols, col + 1);
    }

    /**
     * Gets the project at the specified location.
     *
     * @param row
     *      position of the form
     * @param col
     *      position of the form
     * @return
     *      possibly null.
     */
    @Override
    public ProjectDO get(int row, int col) {
        final Map<Integer, ProjectDO> cols = data.get(row);
        if (cols == null) {
            return null;
        }
        return cols.get(col);
    }

    @Override
    public int getColumns() {
        return cols;
    }

    @Override
    public int getRows() {
        return rows;
    }

    public  Map<Integer/*row*/, Map<Integer/*height*/, ProjectDO>> getData(){return data;}

}
