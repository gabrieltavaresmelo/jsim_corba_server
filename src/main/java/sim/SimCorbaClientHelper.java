package sim;


/**
* sim/SimCorbaClientHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sim.idl
* Sábado, 7 de Dezembro de 2013 20h44min11s BRT
*/

abstract public class SimCorbaClientHelper
{
  private static String  _id = "IDL:sim/SimCorbaClient:1.0";

  public static void insert (org.omg.CORBA.Any a, sim.SimCorbaClient that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static sim.SimCorbaClient extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (sim.SimCorbaClientHelper.id (), "SimCorbaClient");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static sim.SimCorbaClient read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_SimCorbaClientStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, sim.SimCorbaClient value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static sim.SimCorbaClient narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof sim.SimCorbaClient)
      return (sim.SimCorbaClient)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      sim._SimCorbaClientStub stub = new sim._SimCorbaClientStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static sim.SimCorbaClient unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof sim.SimCorbaClient)
      return (sim.SimCorbaClient)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      sim._SimCorbaClientStub stub = new sim._SimCorbaClientStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
