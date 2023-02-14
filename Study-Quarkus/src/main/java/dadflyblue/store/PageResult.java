package dadflyblue.store;

import java.util.Collection;

public class PageResult<T> {
  public int pageIndex;
  public int pageSize;
  public int pageCount;
  public Collection<T> list;

  public static <R> PageResult<R> newPage(int pageIndex, int pageSize, int pageCount, Collection<R> list) {
    var r = new PageResult<R>();
    r.list = list;
    r.pageIndex = pageIndex;
    r.pageSize = pageSize;
    r.pageCount = pageCount;
    return r;
  }
}
