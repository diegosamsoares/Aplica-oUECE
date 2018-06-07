package poo;

public interface IRepository {
IRepository add(Object instance);
IRepository remove (Object instance);
<T> T get(Class<T> instance);
void persistAll ();
}