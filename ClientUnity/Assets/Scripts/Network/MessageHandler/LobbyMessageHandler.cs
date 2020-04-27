﻿using SimpleJSON;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LobbyMessageHandler : MonoBehaviour, IMessageHandler {
	public LobbyManager lobbyManager;
	public GameSceneManager sceneManager;

	public void Handle(string JSONString) {
		ActionJson action = JsonUtility.FromJson<ActionJson>(JSONString);
		Debug.Log("Action recieved : " + action.Action);
		switch (action.Action) {
			case "joinedGame":
				JSONNode joinedGame = JSON.Parse(JSONString);
				int gameId = joinedGame["GameId"];
				int playerId = joinedGame["PlayerId"];
				JSONArray playersRolesJson = joinedGame["PlayersRoles"].AsArray;
				int[] roles = new int[playersRolesJson.Count];
				for (int i = 0; i < playersRolesJson.Count; ++i) {
					roles[i] = playersRolesJson[i];
				}
				GameLobbyData.PlayersRoles = roles;
				lobbyManager.JoinedGame(playerId, roles);
				break;

			case "changedMap":
				JSONNode changedMapJson = JSON.Parse(JSONString);
				string newMap = changedMapJson["Map"];
				lobbyManager.ChangeMap(newMap);
				break;

			case "roleChange":
				JSONNode roleChange = JSON.Parse(JSONString);
				playerId = roleChange["PlayerId"];
				int roleId = roleChange["RoleId"];
				lobbyManager.ChangeRole(playerId, roleId);
				break;

			case "loadLevel":
				JSONNode loadLevelJson = JSON.Parse(JSONString);
				JSONArray blocks = loadLevelJson["Blocks"].AsArray;
				JSONArray objects = loadLevelJson["Objects"].AsArray;
				JSONArray players = loadLevelJson["Players"].AsArray;
				string levelName = loadLevelJson["Name"];

				sceneManager.ToGameboardScene(blocks, objects, players, levelName);
				//model.LoadLevel(blocks, players, objects);
				break;

			case "cantStartGame":
				JSONNode cantStart = JSON.Parse(JSONString);
				string errorMessage = cantStart["MoreInfo"];
				//TODO : Display the error message
				break;
			default:
				Debug.LogError("Can't handle this action : " + action.Action);
				break;
		}
	}
}