package sim;


/**
* sim/_SimCorbaServerStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sim.idl
* Sábado, 7 de Dezembro de 2013 20h44min11s BRT
*/

public class _SimCorbaServerStub extends org.omg.CORBA.portable.ObjectImpl implements sim.SimCorbaServer
{

  public String login (String nickname, sim.SimCorbaClient client) throws sim.UserExists
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("login", true);
                $out.write_string (nickname);
                sim.SimCorbaClientHelper.write ($out, client);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:sim/UserExists:1.0"))
                    throw sim.UserExistsHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return login (nickname, client        );
            } finally {
                _releaseReply ($in);
            }
  } // login

  public void logout (String id) throws sim.AnonymID
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("logout", true);
                $out.write_string (id);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:sim/AnonymID:1.0"))
                    throw sim.AnonymIDHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                logout (id        );
            } finally {
                _releaseReply ($in);
            }
  } // logout

  public void write (String from, String text) throws sim.AnonymID
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("write", true);
                $out.write_string (from);
                $out.write_string (text);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:sim/AnonymID:1.0"))
                    throw sim.AnonymIDHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                write (from, text        );
            } finally {
                _releaseReply ($in);
            }
  } // write

  public void writeTo (String from, String to, String message) throws sim.AnonymID
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("writeTo", true);
                $out.write_string (from);
                $out.write_string (to);
                $out.write_string (message);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                if (_id.equals ("IDL:sim/AnonymID:1.0"))
                    throw sim.AnonymIDHelper.read ($in);
                else
                    throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                writeTo (from, to, message        );
            } finally {
                _releaseReply ($in);
            }
  } // writeTo

  public String get_id (String nickname)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("get_id", true);
                $out.write_string (nickname);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return get_id (nickname        );
            } finally {
                _releaseReply ($in);
            }
  } // get_id

  public void startRadar (String nickname, String location)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("startRadar", true);
                $out.write_string (nickname);
                $out.write_string (location);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                startRadar (nickname, location        );
            } finally {
                _releaseReply ($in);
            }
  } // startRadar

  public void stopRadar (String nickname)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("stopRadar", true);
                $out.write_string (nickname);
                $in = _invoke ($out);
                return;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                stopRadar (nickname        );
            } finally {
                _releaseReply ($in);
            }
  } // stopRadar

  public String changeLocation (String nickname, String location)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("changeLocation", true);
                $out.write_string (nickname);
                $out.write_string (location);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return changeLocation (nickname, location        );
            } finally {
                _releaseReply ($in);
            }
  } // changeLocation

  public String listNearlyClients (String nickname)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("listNearlyClients", true);
                $out.write_string (nickname);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return listNearlyClients (nickname        );
            } finally {
                _releaseReply ($in);
            }
  } // listNearlyClients

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:sim/SimCorbaServer:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _SimCorbaServerStub
