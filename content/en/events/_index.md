---
title: Events
kind: documentation
aliases:
    - /guides/eventcorrelation/
    - /guides/markdown/
    - /graphing/event_stream/
further_reading:
- link: "/api/#events"
  tag: "Documentation"
  text: "Datadog Events API"
---

An event represents any record of activity noteworthy for engineers (devs, ops, and security). See the developer documentation to learn about [submitting events][1] to Datadog.

## Event stream

The [event stream][2] is a display of the most recent events generated by your infrastructure and the associated monitors.

### Search

#### Full text

Full text search works on all keywords provided in the search query after applying filters. Full text search looks inside the event text, title, tags, users who commented on the event, host names, and devices tied to the event.

#### Filters

Target specific event properties using these prefixes:

| Filter                          | Description                                                                    |
|---------------------------------|--------------------------------------------------------------------------------|
| `sources:github,chef`           | Show events from GitHub OR Chef.                                               |
| `tags:env-prod,db`              | Show events tagged with #env-prod OR #db.                                      |
| `hosts:i-0ade23e6,db.myapp.com` | Show events from i-0ade23e6 OR db.myapp.com.                                   |
| `status:error`                  | Show events with an error status (supports: `error`, `warning`, `success`).    |
| `priority:low`                  | Show only low-priority events (supports `low` or `normal`, defaults to `all`). |

**Note**: Filters perform an exact match search. Partial strings are not considered.

#### Advanced

For a more advanced search, use the Datadog event query language, for example:

| Filter                                            | Description                                                               |
|---------------------------------------------------|---------------------------------------------------------------------------|
| `tags:env-prod OR db`                             | Show events tagged with #env-prod OR #db.                                 |
| `tags:security-group:sg-123 AND role:common-node` | Show events tagged with `#security-group:sg-123` AND `#role:common-node`. |
| `cloud_provider:* NOT "azure"`                    | Show all cloud providers except the ones tagged with "azure".             |

Use tag search to find all events with the same key tag, for example:

| Filter               | Description                                                                          |
|----------------------|--------------------------------------------------------------------------------------|
| `tags:<KEY>:<VALUE>` | Shows events with the `<KEY>:<VALUE>` tag.                                           |
| `<KEY>:*`            | Shows all events with the `<KEY>` attached.                                          |
| `<KEY>`:`<REGEX>`    | Shows all events with `<KEY>:<VALUE>` tag where the `<VALUE>` matches the `<REGEX>`. |
| `tags:<KEY>`         | This is not a valid search.                                                          |
| `<KEY>:<VALUE>`      | This is not a valid search.                                                          |

To combine multiple terms into a complex query, use the following Boolean operators:

| Operator | Description                                                                                                           | Example                                   |
|----------|-----------------------------------------------------------------------------------------------------------------------|-------------------------------------------|
| `AND`    | **Intersection**: both terms are in the selected events (for tags, if nothing is added, `AND` is the default).        | `redis_* AND down`                        |
| `OR`     | **Union**: either term is contained in the selected events. Use a comma (`,`) for tags.                               | `sources:nagios,chef directory OR Mixlib` |
| `NOT`    | **Exclusion**: the following term is NOT in the event. This operator works for strings only—use `-` in front of tags. | `-tags:<KEY>:<VALUE> NOT "<STRING>"`      |


**Note**: Some of the advanced query language features like Boolean logic work only in the event stream page, and are not available in graph tiles or dashboard widgets.

Combine prefixes to construct more complex searches. For example, to find all open `chef` or `nagios` errors that mention `cassandra`, use:

```
sources:nagios,chef status:error cassandra
```

**Note**: Do not use spaces after the colon or commas in these lists. Anything not attached to a prefix goes to full text search.

### Aggregation

By default, related events are aggregated when displayed in the events stream. To show unaggregated events, un-check the **Aggregate related events** box at the top right of your event stream:

{{< img src="graphing/events/event_stream_aggregated.png" alt="Aggregated event stream" responsive="true" style="width:50%;" >}}

### Notifications

Datadog supports `@notifications` in the event stream, for example:

| Example                                 | Description                                                                                      |
|-----------------------------------------|--------------------------------------------------------------------------------------------------|
| `@support-datadog`                      | Creates a Datadog support ticket when posted directly to your event stream (including comments). |
| `@all`                                  | Sends a notification to all members of your organization.                                        |
| `@john`                                 | Notifies the user named `john`.                                                                  |
| `@test@example.com`                     | Sends an email to `test@example.com`.                                                            |
| `@slack-<SLACK_ACCOUNT>-<CHANNEL_NAME>` | Posts the event or graph to the specified Slack channel.                                         |
| `@webhook`                              | Alerts or triggers the webhook. See the [blog post on webhooks][3].                         |
| `@pagerduty`                            | Sends an alert to Pagerduty. You can also use `@pagerduty-acknowledge` and `@pagerduty-resolve`. |

## Further Reading

{{< partial name="whats-next/whats-next.html" >}}


[1]: /developers/events/
[2]: https://app.datadoghq.com/event/stream
[3]: https://www.datadoghq.com/blog/send-alerts-sms-customizable-webhooks-twilio