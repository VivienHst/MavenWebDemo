package dev.vivienhuang.mavenwebdemo.linebot.webhook;

import java.util.List;

public class WebhookModel {
	
	/*
	 * 
	 * {
		  "destination": "xxxxxxxxxx", 
		  "events": [
		    {
		      "replyToken": "0f3779fba3b349968c5d07db31eab56f",
		      "type": "message",
		      "mode": "active",
		      "timestamp": 1462629479859,
		      "source": {
		        "type": "user",
		        "userId": "U4af4980629..."
		      },
		      "message": {
		        "id": "325708",
		        "type": "text",
		        "text": "Hello, world"
		      }
		    },
		    {
		      "replyToken": "8cf9239d56244f4197887e939187e19e",
		      "type": "follow",
		      "mode": "active",
		      "timestamp": 1462629479859,
		      "source": {
		        "type": "user",
		        "userId": "U4af4980629..."
		      }
		    }
		  ]
		}
		*/

	private String destination;
	
	private List<EventModel> events;

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<EventModel> getEvents() {
		return events;
	}

	public void setEvents(List<EventModel> events) {
		this.events = events;
	}
	
}
