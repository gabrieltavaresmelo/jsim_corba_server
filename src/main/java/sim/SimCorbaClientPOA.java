package sim;


/**
* sim/SimCorbaClientPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sim.idl
* Sábado, 7 de Dezembro de 2013 20h44min11s BRT
*/

public abstract class SimCorbaClientPOA extends org.omg.PortableServer.Servant
 implements sim.SimCorbaClientOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("update", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // sim/SimCorbaClient/update
       {
         String message = in.read_string ();
         this.update (message);
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:sim/SimCorbaClient:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public SimCorbaClient _this() 
  {
    return SimCorbaClientHelper.narrow(
    super._this_object());
  }

  public SimCorbaClient _this(org.omg.CORBA.ORB orb) 
  {
    return SimCorbaClientHelper.narrow(
    super._this_object(orb));
  }


} // class SimCorbaClientPOA
