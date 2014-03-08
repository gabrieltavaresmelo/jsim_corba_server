package sim;


/**
* sim/SimCorbaServerOperations.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from sim.idl
* Sábado, 7 de Dezembro de 2013 20h44min11s BRT
*/

public interface SimCorbaServerOperations 
{
  String login (String nickname, sim.SimCorbaClient client) throws sim.UserExists;
  void logout (String id) throws sim.AnonymID;
  void write (String from, String text) throws sim.AnonymID;
  void writeTo (String from, String to, String message) throws sim.AnonymID;
  String get_id (String nickname);
  void startRadar (String nickname, String location);
  void stopRadar (String nickname);
  String changeLocation (String nickname, String location);
  String listNearlyClients (String nickname);
} // interface SimCorbaServerOperations