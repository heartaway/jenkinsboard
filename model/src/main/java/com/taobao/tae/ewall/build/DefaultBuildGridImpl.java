package com.taobao.tae.ewall.build;

import com.taobao.tae.ewall.project.ProjectDO;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link BuildGrid} implementation backed by a sparse array.
 *
 * @author Kohsuke Kawaguchi
 */
public class DefaultBuildGridImpl extends BuildGrid {
    /**
     * Actual data.
     */
    protected final Map<Integer/*row*/, Map<Integer/*height*/, BuildProjectDO>> data = new HashMap<Integer, Map<Integer, BuildProjectDO>>();

    /**
     * Dimension of the {@link #data}
     */
    private int rows, cols;

    /**
     * Mutable, but only for the code that instantiates {@link DefaultBuildGridImpl}.
     *
     * @param row
     *      position of the form
     * @param col
     *      position of the form
     * @param p
     *      The build to add. null to remove the value.
     */
    public void set(int row, int col, BuildProjectDO p) {
        Map<Integer, BuildProjectDO> c = data.get(row);
        if (c == null) {
            c = new HashMap<Integer, BuildProjectDO>();
            data.put(row, c);
        }
        c.put(col, p);

        rows = Math.max(rows, row + 1);
        cols = Math.max(cols, col + 1);
    }

    @Override
    public BuildProjectDO get(int row, int col) {
        final Map<Integer, BuildProjectDO> cols = data.get(row);
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

    public  Map<Integer/*row*/, Map<Integer/*height*/, BuildProjectDO>> getData(){return data;}
}
