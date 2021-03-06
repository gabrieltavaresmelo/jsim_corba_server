package sim;


/**
* sim/SimCorbaServerHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sim.idl
* Sábado, 7 de Dezembro de 2013 20h44min11s BRT
*/

abstract public class SimCorbaServerHelper
{
  private static String  _id = "IDL:sim/SimCorbaServer:1.0";

  public static void insert (org.omg.CORBA.Any a, sim.SimCorbaServer that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static sim.SimCorbaServer extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (sim.SimCorbaServerHelper.id (), "SimCorbaServer");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static sim.SimCorbaServer read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_SimCorbaServerStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, sim.SimCorbaServer value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static sim.SimCorbaServer narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof sim.SimCorbaServer)
      return (sim.SimCorbaServer)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      sim._SimCorbaServerStub stub = new sim._SimCorbaServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static sim.SimCorbaServer unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof sim.SimCorbaServer)
      return (sim.SimCorbaServer)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      sim._SimCorbaServerStub stub = new sim._SimCorbaServerStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
