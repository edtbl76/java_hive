import java.util.*;


/**
 * @author Paulene Dean
 * @version 1.0
 * @created 13-Oct-2021 11:10:23 PM
 */
public class Order implements StateMachineContext {

	private Date date;
	private String deliveryInstructions;
	private String orderNumber;
	private LineItem m_LineItem;

	public void checkForOutstandingOrders(){
		doCheckForOutstandingOrders();
	}

	public Date getDate(){
		return date;
	}

	public String getDeliveryInstructions(){
		return deliveryInstructions;
	}

	public LineItem getLineItem(){
		return m_LineItem;
	}

	public String getOrderNumber(){
		return orderNumber;
	}

	public OrderStatus getStatus(){
		return status;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDate(Date newVal){
		date = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setDeliveryInstructions(String newVal){
		deliveryInstructions = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setLineItem(LineItem newVal){
		m_LineItem = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setOrderNumber(String newVal){
		orderNumber = newVal;
	}

	/**
	 * 
	 * @param newVal
	 */
	public void setStatus(OrderStatus newVal){
		status = newVal;
	}
	/* Begin - EA generated code for Parts and Ports */
	/*
	Status Variable
	*/
	public OrderStatus status ;
	/* End - EA generated code for Parts and Ports */
	/* Begin - EA generated code for  Activities and Interactions */
	public void doCheckForOutstandingOrders()
	{
		// behavior is a Activity
		if (status != OrderStatus.closed)
		{
			initializeStateMachine();
			while (status != OrderStatus.closed)
			{
				runStateMachine();
			}
		}
		else
		{
			//No Outstanding orders;
		}
		return;
	}

	/* End - EA generated code for  Activities and Interactions */
	/* Begin - EA generated code for StateMachine */


	enum StateMachineEnum 
	{
		NOSTATEMACHINE,
		Order_ENUM_PROCESSORDER
	};

	enum StateEnum 
	{
		NOSTATE,
		Order_VIRTUAL_SUBMACHINESTATE,
		Order_ENUM_PROCESSORDER_CLOSED,
		Order_ENUM_PROCESSORDER_DISPATCHED,
		Order_ENUM_PROCESSORDER_NEW,
		Order_ENUM_PROCESSORDER_DELIVERED,
		Order_ENUM_PROCESSORDER_PACKED
	};

	enum TransitionEnum 
	{
		NOTRANSITION,
		Order_ENUM_INITIAL_2612_2612__TO__NEW_1584,
		Order_ENUM_CLOSED__TO__EXITPOINT_2580_2580_1614,
		Order_ENUM_DISPATCHED__TO__DELIVERED_1586,
		Order_ENUM_PACKED__TO__DISPATCHED_1585,
		Order_ENUM_NEW__TO__PACKED_1583,
		Order_ENUM_DELIVERED__TO__CLOSED_1594
	};

	enum EntryEnum
	{
		NOENTRY,
		Order_ENUM_PROCESSORDER_INITIAL_2612_2612
	};

	public StateMachineImpl m_StateMachineImpl;	
	
	public Order()
	{
		m_StateMachineImpl = null;
	}
	
	protected void finalize( ) throws Throwable
	{
	}
	
	public Order(ContextManager pManager, String sInstanceName)
	{
		//Initialize Region Variables
		m_processorder = StateEnum.NOSTATE;	
		m_sInstanceName = sInstanceName;
		m_sType = "Order";
		m_StateMachineImpl = new StateMachineImpl(this, pManager);
	}
	
    public String m_sInstanceName;
    public String m_sType;
	
	public String GetInstanceName()
	{
		return m_sInstanceName;
	}
	
	public String GetTypeName()
	{
		return m_sType;
	}
	
	public boolean defer(Event event, StateData toState)
	{
		if (m_StateMachineImpl == null)
			return false;
		
		boolean bDefer = false;
    
		
		if(!bDefer)
		{
			if(toState.parent_state != null)
			{
				bDefer = defer(event, toState.parent_state);
			}
		}
		return bDefer;
	}

	public void TransitionProc(TransitionEnum transition, Signal signal, StateData submachineState)
	{
		if (m_StateMachineImpl == null)
			return;
		
	
		switch (transition) 
		{
			case Order_ENUM_INITIAL_2612_2612__TO__NEW_1584:
				m_StateMachineImpl.currentTransition.SetTransition(transition.ordinal(), submachineState, "Order_initial_2612_2612__TO__New_1584", "{0A901D4A-BD0C-460f-AE6E-148CB7B6ACDC}",m_sInstanceName);
				initial_2612_2612__TO__New_1584(signal, submachineState); 
				break;
			case Order_ENUM_CLOSED__TO__EXITPOINT_2580_2580_1614:
				m_StateMachineImpl.currentTransition.SetTransition(transition.ordinal(), submachineState, "Order_Closed__TO__exitPoint_2580_2580_1614", "{17AF1882-8C86-4bfe-B31B-E1A76356D7B0}",m_sInstanceName);
				Closed__TO__exitPoint_2580_2580_1614(signal, submachineState); 
				break;
			case Order_ENUM_DISPATCHED__TO__DELIVERED_1586:
				m_StateMachineImpl.currentTransition.SetTransition(transition.ordinal(), submachineState, "Order_Dispatched__TO__Delivered_1586", "{1EE7572C-4D9D-472d-B143-4E0B904F3F25}",m_sInstanceName);
				Dispatched__TO__Delivered_1586(signal, submachineState); 
				break;
			case Order_ENUM_PACKED__TO__DISPATCHED_1585:
				m_StateMachineImpl.currentTransition.SetTransition(transition.ordinal(), submachineState, "Order_Packed__TO__Dispatched_1585", "{2A375E8B-1132-4247-9E71-78CD9483657C}",m_sInstanceName);
				Packed__TO__Dispatched_1585(signal, submachineState); 
				break;
			case Order_ENUM_NEW__TO__PACKED_1583:
				m_StateMachineImpl.currentTransition.SetTransition(transition.ordinal(), submachineState, "Order_New__TO__Packed_1583", "{54E4CF67-9D99-42aa-B739-EA6C3BBF5A33}",m_sInstanceName);
				New__TO__Packed_1583(signal, submachineState); 
				break;
			case Order_ENUM_DELIVERED__TO__CLOSED_1594:
				m_StateMachineImpl.currentTransition.SetTransition(transition.ordinal(), submachineState, "Order_Delivered__TO__Closed_1594", "{93B83F56-0A8E-4bee-87E3-7E8F0990DB0A}",m_sInstanceName);
				Delivered__TO__Closed_1594(signal, submachineState); 
				break;
		}
	
		m_StateMachineImpl.currentTransition.SetTransition(TransitionEnum.NOTRANSITION.ordinal(), null, "", "","");	
	}
	
	private void initial_2612_2612__TO__New_1584_effect(Signal signal)
	{
		GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Transition Effect: initial_2612_2612__TO__New_1584");
	
	}

	private void initial_2612_2612__TO__New_1584(Signal signal, StateData submachineState) 
	{
		if (m_StateMachineImpl == null)
			return;
	
		if(submachineState != null)
			submachineState.IncrementActiveCount();
		initial_2612_2612__TO__New_1584_effect(signal);
		m_StateMachineImpl.currentTransition.SetActive(m_StateMachineImpl);
		StateProc(StateEnum.Order_ENUM_PROCESSORDER_NEW, submachineState, StateBehaviorEnum.ENTRY, signal, EntryTypeEnum.DefaultEntry);
	}
	private void Closed__TO__exitPoint_2580_2580_1614_effect(Signal signal)
	{
		GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Transition Effect: Closed__TO__exitPoint_2580_2580_1614");
	
	}

	private void Closed__TO__exitPoint_2580_2580_1614(Signal signal, StateData submachineState) 
	{
		if (m_StateMachineImpl == null)
			return;
	
		if(!m_StateMachineImpl.GetStateObject(submachineState, StateEnum.Order_ENUM_PROCESSORDER_CLOSED.ordinal()).IsActiveState())
		{
			return;
		}
		StateProc(StateEnum.Order_ENUM_PROCESSORDER_CLOSED, submachineState, StateBehaviorEnum.EXIT, null);
		Closed__TO__exitPoint_2580_2580_1614_effect(signal);
		m_StateMachineImpl.currentTransition.SetActive(m_StateMachineImpl);
		submachineState.DecrementActiveCount();
		if(submachineState.IsActiveState())
			m_StateMachineImpl.deferInternalEvent(EventProxy.EventEnum.EXIT_Order_ProcessOrder_exitPoint_2580_2580, signal, submachineState);
	
	
	}
	private void Dispatched__TO__Delivered_1586_effect(Signal signal)
	{
		GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Transition Effect: Dispatched__TO__Delivered_1586");
	
	}

	private void Dispatched__TO__Delivered_1586(Signal signal, StateData submachineState) 
	{
		if (m_StateMachineImpl == null)
			return;
	
		if(!m_StateMachineImpl.GetStateObject(submachineState, StateEnum.Order_ENUM_PROCESSORDER_DISPATCHED.ordinal()).IsActiveState())
		{
			return;
		}
		StateProc(StateEnum.Order_ENUM_PROCESSORDER_DISPATCHED, submachineState, StateBehaviorEnum.EXIT, null);
		Dispatched__TO__Delivered_1586_effect(signal);
		m_StateMachineImpl.currentTransition.SetActive(m_StateMachineImpl);
		StateProc(StateEnum.Order_ENUM_PROCESSORDER_DELIVERED, submachineState, StateBehaviorEnum.ENTRY, signal, EntryTypeEnum.DefaultEntry);
	}
	private void Packed__TO__Dispatched_1585_effect(Signal signal)
	{
		GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Transition Effect: Packed__TO__Dispatched_1585");
	
	}

	private void Packed__TO__Dispatched_1585(Signal signal, StateData submachineState) 
	{
		if (m_StateMachineImpl == null)
			return;
	
		if(!m_StateMachineImpl.GetStateObject(submachineState, StateEnum.Order_ENUM_PROCESSORDER_PACKED.ordinal()).IsActiveState())
		{
			return;
		}
		StateProc(StateEnum.Order_ENUM_PROCESSORDER_PACKED, submachineState, StateBehaviorEnum.EXIT, null);
		Packed__TO__Dispatched_1585_effect(signal);
		m_StateMachineImpl.currentTransition.SetActive(m_StateMachineImpl);
		StateProc(StateEnum.Order_ENUM_PROCESSORDER_DISPATCHED, submachineState, StateBehaviorEnum.ENTRY, signal, EntryTypeEnum.DefaultEntry);
	}
	private void New__TO__Packed_1583_effect(Signal signal)
	{
		GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Transition Effect: New__TO__Packed_1583");
	
	}

	private void New__TO__Packed_1583(Signal signal, StateData submachineState) 
	{
		if (m_StateMachineImpl == null)
			return;
	
		if(!m_StateMachineImpl.GetStateObject(submachineState, StateEnum.Order_ENUM_PROCESSORDER_NEW.ordinal()).IsActiveState())
		{
			return;
		}
		StateProc(StateEnum.Order_ENUM_PROCESSORDER_NEW, submachineState, StateBehaviorEnum.EXIT, null);
		New__TO__Packed_1583_effect(signal);
		m_StateMachineImpl.currentTransition.SetActive(m_StateMachineImpl);
		StateProc(StateEnum.Order_ENUM_PROCESSORDER_PACKED, submachineState, StateBehaviorEnum.ENTRY, signal, EntryTypeEnum.DefaultEntry);
	}
	private void Delivered__TO__Closed_1594_effect(Signal signal)
	{
		GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Transition Effect: Delivered__TO__Closed_1594");
	
		setStatus(OrderStatus.closed)

	}

	private void Delivered__TO__Closed_1594(Signal signal, StateData submachineState) 
	{
		if (m_StateMachineImpl == null)
			return;
	
		if(!m_StateMachineImpl.GetStateObject(submachineState, StateEnum.Order_ENUM_PROCESSORDER_DELIVERED.ordinal()).IsActiveState())
		{
			return;
		}
		StateProc(StateEnum.Order_ENUM_PROCESSORDER_DELIVERED, submachineState, StateBehaviorEnum.EXIT, null);
		Delivered__TO__Closed_1594_effect(signal);
		m_StateMachineImpl.currentTransition.SetActive(m_StateMachineImpl);
		StateProc(StateEnum.Order_ENUM_PROCESSORDER_CLOSED, submachineState, StateBehaviorEnum.ENTRY, signal, EntryTypeEnum.DefaultEntry);
	}
	public boolean StateProc(StateEnum state, StateData submachineState, StateBehaviorEnum behavior, Signal signal)
	{
		return StateProc(state, submachineState, behavior, signal, EntryTypeEnum.DefaultEntry, null, 0);
	}
	
	public boolean StateProc(StateEnum state, StateData submachineState, StateBehaviorEnum behavior, Signal signal, EntryTypeEnum enumEntryType)
	{
		return StateProc(state, submachineState, behavior, signal, enumEntryType, null, 0);
	}
	
	public boolean StateProc(int state, StateData submachineState, StateBehaviorEnum behavior, Signal signal, EntryTypeEnum enumEntryType, int[] entryArray, int nArrayCount)
    {
		ArrayList<EntryEnum> entryEnumList = new ArrayList<>();
		if (entryArray != null)
		{
			for(int nEntryIndex : entryArray)
			{
				entryEnumList.add(EntryEnum.values()[nEntryIndex]);
			}
		}
        return StateProc(StateEnum.values()[state], submachineState, behavior, signal, enumEntryType, entryEnumList.toArray(new EntryEnum[entryEnumList.size()]), nArrayCount);
    }
	
	public boolean StateProc(StateEnum state, StateData submachineState, StateBehaviorEnum behavior, Signal signal, EntryTypeEnum enumEntryType, EntryEnum[] entryArray, int nArrayCount)
	{
		switch (state) 
		{
			case Order_ENUM_PROCESSORDER_CLOSED:
				ProcessOrder_Closed(behavior, submachineState, signal, enumEntryType, entryArray, nArrayCount);
				break;

			case Order_ENUM_PROCESSORDER_DISPATCHED:
				ProcessOrder_Dispatched(behavior, submachineState, signal, enumEntryType, entryArray, nArrayCount);
				break;

			case Order_ENUM_PROCESSORDER_NEW:
				ProcessOrder_New(behavior, submachineState, signal, enumEntryType, entryArray, nArrayCount);
				break;

			case Order_ENUM_PROCESSORDER_DELIVERED:
				ProcessOrder_Delivered(behavior, submachineState, signal, enumEntryType, entryArray, nArrayCount);
				break;

			case Order_ENUM_PROCESSORDER_PACKED:
				ProcessOrder_Packed(behavior, submachineState, signal, enumEntryType, entryArray, nArrayCount);
				break;
		}
		return false;
	}
	
	public boolean ProcessOrder_Closed(StateBehaviorEnum behavior, StateData submachineState, Signal signal, EntryTypeEnum enumEntryType, EntryEnum[] entryArray, int nArrayCount) 
	{
		if (m_StateMachineImpl == null)
			return false;
	
		StateData state = m_StateMachineImpl.GetStateObject(submachineState, StateEnum.Order_ENUM_PROCESSORDER_CLOSED.ordinal());
		switch (behavior) 
		{
			case ENTRY:
				if(state.active_count > 0)
					return false;
				m_processorder = StateEnum.Order_ENUM_PROCESSORDER_CLOSED;
				state.IncrementActiveCount();
				ProcessOrder_Closed_behavior(StateBehaviorEnum.ENTRY);
						
				ProcessOrder_Closed_behavior(StateBehaviorEnum.DO);
			
				if((enumEntryType == EntryTypeEnum.EntryPointEntry || enumEntryType == EntryTypeEnum.DefaultEntry) && state.IsActiveState())
					m_StateMachineImpl.deferInternalEvent(EventProxy.EventEnum.COMPLETION, null, state);
				break;
			case EXIT:
				if(state.active_count == 0)
					return false;
				m_processorder = StateEnum.NOSTATE;
				state.DecrementActiveCount();
				ProcessOrder_Closed_behavior(StateBehaviorEnum.EXIT);
				m_StateMachineImpl.removeInternalEvent(state);
				break;
		}
	
		return true;
	}

	public boolean ProcessOrder_Closed_behavior(StateBehaviorEnum behavior)
	{
		switch (behavior) 
		{
			case ENTRY:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Entry Behavior: ProcessOrder_Closed");
			}
			break;
			case DO:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Do Behavior: ProcessOrder_Closed");
			}
			break;
			case EXIT:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Exit Behavior: ProcessOrder_Closed");
			}
			break;
		}
	
		return true;
	}
	public boolean ProcessOrder_Dispatched(StateBehaviorEnum behavior, StateData submachineState, Signal signal, EntryTypeEnum enumEntryType, EntryEnum[] entryArray, int nArrayCount) 
	{
		if (m_StateMachineImpl == null)
			return false;
	
		StateData state = m_StateMachineImpl.GetStateObject(submachineState, StateEnum.Order_ENUM_PROCESSORDER_DISPATCHED.ordinal());
		switch (behavior) 
		{
			case ENTRY:
				if(state.active_count > 0)
					return false;
				m_processorder = StateEnum.Order_ENUM_PROCESSORDER_DISPATCHED;
				state.IncrementActiveCount();
				ProcessOrder_Dispatched_behavior(StateBehaviorEnum.ENTRY);
						
				ProcessOrder_Dispatched_behavior(StateBehaviorEnum.DO);
			
				if((enumEntryType == EntryTypeEnum.EntryPointEntry || enumEntryType == EntryTypeEnum.DefaultEntry) && state.IsActiveState())
					m_StateMachineImpl.deferInternalEvent(EventProxy.EventEnum.COMPLETION, null, state);
				break;
			case EXIT:
				if(state.active_count == 0)
					return false;
				m_processorder = StateEnum.NOSTATE;
				state.DecrementActiveCount();
				ProcessOrder_Dispatched_behavior(StateBehaviorEnum.EXIT);
				m_StateMachineImpl.removeInternalEvent(state);
				break;
		}
	
		return true;
	}

	public boolean ProcessOrder_Dispatched_behavior(StateBehaviorEnum behavior)
	{
		switch (behavior) 
		{
			case ENTRY:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Entry Behavior: ProcessOrder_Dispatched");
			}
			break;
			case DO:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Do Behavior: ProcessOrder_Dispatched");
				setStatus(OrderStatus.dispatched)
			}
			break;
			case EXIT:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Exit Behavior: ProcessOrder_Dispatched");
			}
			break;
		}
	
		return true;
	}
	public boolean ProcessOrder_New(StateBehaviorEnum behavior, StateData submachineState, Signal signal, EntryTypeEnum enumEntryType, EntryEnum[] entryArray, int nArrayCount) 
	{
		if (m_StateMachineImpl == null)
			return false;
	
		StateData state = m_StateMachineImpl.GetStateObject(submachineState, StateEnum.Order_ENUM_PROCESSORDER_NEW.ordinal());
		switch (behavior) 
		{
			case ENTRY:
				if(state.active_count > 0)
					return false;
				m_processorder = StateEnum.Order_ENUM_PROCESSORDER_NEW;
				state.IncrementActiveCount();
				ProcessOrder_New_behavior(StateBehaviorEnum.ENTRY);
						
				ProcessOrder_New_behavior(StateBehaviorEnum.DO);
			
				if((enumEntryType == EntryTypeEnum.EntryPointEntry || enumEntryType == EntryTypeEnum.DefaultEntry) && state.IsActiveState())
					m_StateMachineImpl.deferInternalEvent(EventProxy.EventEnum.COMPLETION, null, state);
				break;
			case EXIT:
				if(state.active_count == 0)
					return false;
				m_processorder = StateEnum.NOSTATE;
				state.DecrementActiveCount();
				ProcessOrder_New_behavior(StateBehaviorEnum.EXIT);
				m_StateMachineImpl.removeInternalEvent(state);
				break;
		}
	
		return true;
	}

	public boolean ProcessOrder_New_behavior(StateBehaviorEnum behavior)
	{
		switch (behavior) 
		{
			case ENTRY:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Entry Behavior: ProcessOrder_New");
			}
			break;
			case DO:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Do Behavior: ProcessOrder_New");
				setStatus(OrderStatus.New)
			}
			break;
			case EXIT:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Exit Behavior: ProcessOrder_New");
			}
			break;
		}
	
		return true;
	}
	public boolean ProcessOrder_Delivered(StateBehaviorEnum behavior, StateData submachineState, Signal signal, EntryTypeEnum enumEntryType, EntryEnum[] entryArray, int nArrayCount) 
	{
		if (m_StateMachineImpl == null)
			return false;
	
		StateData state = m_StateMachineImpl.GetStateObject(submachineState, StateEnum.Order_ENUM_PROCESSORDER_DELIVERED.ordinal());
		switch (behavior) 
		{
			case ENTRY:
				if(state.active_count > 0)
					return false;
				m_processorder = StateEnum.Order_ENUM_PROCESSORDER_DELIVERED;
				state.IncrementActiveCount();
				ProcessOrder_Delivered_behavior(StateBehaviorEnum.ENTRY);
						
				ProcessOrder_Delivered_behavior(StateBehaviorEnum.DO);
			
				if((enumEntryType == EntryTypeEnum.EntryPointEntry || enumEntryType == EntryTypeEnum.DefaultEntry) && state.IsActiveState())
					m_StateMachineImpl.deferInternalEvent(EventProxy.EventEnum.COMPLETION, null, state);
				break;
			case EXIT:
				if(state.active_count == 0)
					return false;
				m_processorder = StateEnum.NOSTATE;
				state.DecrementActiveCount();
				ProcessOrder_Delivered_behavior(StateBehaviorEnum.EXIT);
				m_StateMachineImpl.removeInternalEvent(state);
				break;
		}
	
		return true;
	}

	public boolean ProcessOrder_Delivered_behavior(StateBehaviorEnum behavior)
	{
		switch (behavior) 
		{
			case ENTRY:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Entry Behavior: ProcessOrder_Delivered");
			}
			break;
			case DO:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Do Behavior: ProcessOrder_Delivered");
				setStatus(OrderStatus.delivered)
			}
			break;
			case EXIT:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Exit Behavior: ProcessOrder_Delivered");
			}
			break;
		}
	
		return true;
	}
	public boolean ProcessOrder_Packed(StateBehaviorEnum behavior, StateData submachineState, Signal signal, EntryTypeEnum enumEntryType, EntryEnum[] entryArray, int nArrayCount) 
	{
		if (m_StateMachineImpl == null)
			return false;
	
		StateData state = m_StateMachineImpl.GetStateObject(submachineState, StateEnum.Order_ENUM_PROCESSORDER_PACKED.ordinal());
		switch (behavior) 
		{
			case ENTRY:
				if(state.active_count > 0)
					return false;
				m_processorder = StateEnum.Order_ENUM_PROCESSORDER_PACKED;
				state.IncrementActiveCount();
				ProcessOrder_Packed_behavior(StateBehaviorEnum.ENTRY);
						
				ProcessOrder_Packed_behavior(StateBehaviorEnum.DO);
			
				if((enumEntryType == EntryTypeEnum.EntryPointEntry || enumEntryType == EntryTypeEnum.DefaultEntry) && state.IsActiveState())
					m_StateMachineImpl.deferInternalEvent(EventProxy.EventEnum.COMPLETION, null, state);
				break;
			case EXIT:
				if(state.active_count == 0)
					return false;
				m_processorder = StateEnum.NOSTATE;
				state.DecrementActiveCount();
				ProcessOrder_Packed_behavior(StateBehaviorEnum.EXIT);
				m_StateMachineImpl.removeInternalEvent(state);
				break;
		}
	
		return true;
	}

	public boolean ProcessOrder_Packed_behavior(StateBehaviorEnum behavior)
	{
		switch (behavior) 
		{
			case ENTRY:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Entry Behavior: ProcessOrder_Packed");
			}
			break;
			case DO:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Do Behavior: ProcessOrder_Packed");
				setStatus(OrderStatus.packed)
			}
			break;
			case EXIT:
			{
				GlobalFuncs.trace("[" + m_sInstanceName + ":" + m_sType + "] Exit Behavior: ProcessOrder_Packed");
			}
			break;
		}
	
		return true;
	}
	public boolean dispatch(Event event, StateData toState, boolean bCheckOnly)
	{
		if (m_StateMachineImpl == null)
			return false;
		
		boolean bDispatched = false;
		if(toState == null || !toState.IsActiveState() && !bCheckOnly)
			return bDispatched;
		
		switch (StateEnum.values()[toState.state_enum]) 
		{
			case Order_VIRTUAL_SUBMACHINESTATE:
				if(event.eventEnum == EventProxy.EventEnum.COMPLETION)
				{
					if(!bCheckOnly)
					{
						m_StateMachineImpl.ReleaseSubmachineState(toState);
						for (int index = m_StateMachineImpl.lstStateData.size() - 1; index >= 0; index--)
						{
							StateData state = m_StateMachineImpl.lstStateData.get(index);
							if (state == toState)
							{
								m_StateMachineImpl.lstStateData.remove(state);
								break;
							}						
						}
						//delete toState;
						toState = null;
					}				
					bDispatched = true;
				}
				break;
			case Order_ENUM_PROCESSORDER_CLOSED:
				switch (event.eventEnum) 
				{
					case COMPLETION:
						if(!bCheckOnly)
							TransitionProc(TransitionEnum.Order_ENUM_CLOSED__TO__EXITPOINT_2580_2580_1614, null, toState.submachine_state);
						bDispatched = true;
						break;
				}
				break;
			case Order_ENUM_PROCESSORDER_DISPATCHED:
				switch (event.eventEnum) 
				{
					case COMPLETION:
						if(!bCheckOnly)
							TransitionProc(TransitionEnum.Order_ENUM_DISPATCHED__TO__DELIVERED_1586, null, toState.submachine_state);
						bDispatched = true;
						break;
				}
				break;
			case Order_ENUM_PROCESSORDER_NEW:
				switch (event.eventEnum) 
				{
					case COMPLETION:
						if(!bCheckOnly)
							TransitionProc(TransitionEnum.Order_ENUM_NEW__TO__PACKED_1583, null, toState.submachine_state);
						bDispatched = true;
						break;
				}
				break;
			case Order_ENUM_PROCESSORDER_DELIVERED:
				switch (event.eventEnum) 
				{
					case COMPLETION:
						if(status==OrderStatus.delivered) {
							if(!bCheckOnly)
								TransitionProc(TransitionEnum.Order_ENUM_DELIVERED__TO__CLOSED_1594, null, toState.submachine_state);
							bDispatched = true;
							break;
						}
						break;
				}
				break;
			case Order_ENUM_PROCESSORDER_PACKED:
				switch (event.eventEnum) 
				{
					case COMPLETION:
						if(!bCheckOnly)
							TransitionProc(TransitionEnum.Order_ENUM_PACKED__TO__DISPATCHED_1585, null, toState.submachine_state);
						bDispatched = true;
						break;
				}
				break;
		}
		
		if (!bDispatched && toState != null && toState.parent_state != null && event.eventEnum != EventProxy.EventEnum.COMPLETION)
		{
			bDispatched = dispatch(event, toState.parent_state, true);
			if (bDispatched && !bCheckOnly)
			{
				/*1. Exit the current state; 2. Decrement the active count of the parent state; 3. dispatch the event to parent state*/
				StateProc(StateEnum.values()[toState.state_enum], toState.submachine_state, StateBehaviorEnum.EXIT, null);
				toState.parent_state.DecrementActiveCount();
				dispatch(event, toState.parent_state, false);
				event = null;
			}
		}
		
		return bDispatched;
	}
	
	StateEnum m_processorder;
	
	@Override
    public void runStateMachine(String statemachine)
    {
		if(statemachine.equals("Order_ProcessOrder"))
		{
			runStateMachine(StateMachineEnum.Order_ENUM_PROCESSORDER);
			return;
		}
    }
	
	private void runStateMachine(StateMachineEnum statemachine, StateData submachineState, Signal signal)
	{
		runStateMachine(statemachine, submachineState, signal, null, 0);
	}
	
	private void runStateMachine(StateMachineEnum statemachine, StateData submachineState, Signal signal, EntryEnum[] entryArray, int nEntryCount)
	{
		if (m_StateMachineImpl == null)
			return;
		
		if(submachineState == null)
		{
			StateInitialData initialData = new StateInitialData(StateEnum.Order_VIRTUAL_SUBMACHINESTATE.ordinal(), StateEnum.NOSTATE.ordinal(), 1, false, "Order_VIRTUAL_SUBMACHINESTATE", "", ""); 
			submachineState = new StateData(m_StateMachineImpl, initialData);
			submachineState.IncrementActiveCount();
			m_StateMachineImpl.lstStateData.add(submachineState);
		}
		switch (statemachine) 
		{
			case Order_ENUM_PROCESSORDER:
				{
					final int nArrayCount = 5;
					StateInitialData[] initialDataArray = 
						{
							new StateInitialData(StateEnum.Order_ENUM_PROCESSORDER_CLOSED.ordinal(), StateEnum.NOSTATE.ordinal(), 0, false, "Order_ProcessOrder_Closed", "{5B3DDE39-AABB-4caa-A5AA-31D36993B342}", m_sInstanceName),
							new StateInitialData(StateEnum.Order_ENUM_PROCESSORDER_DISPATCHED.ordinal(), StateEnum.NOSTATE.ordinal(), 0, false, "Order_ProcessOrder_Dispatched", "{81F98ECB-CA78-47cf-AC83-5391CCBA3409}", m_sInstanceName),
							new StateInitialData(StateEnum.Order_ENUM_PROCESSORDER_NEW.ordinal(), StateEnum.NOSTATE.ordinal(), 0, false, "Order_ProcessOrder_New", "{88241FC6-24FF-492f-9B25-E76BC590149A}", m_sInstanceName),
							new StateInitialData(StateEnum.Order_ENUM_PROCESSORDER_DELIVERED.ordinal(), StateEnum.NOSTATE.ordinal(), 0, false, "Order_ProcessOrder_Delivered", "{ADAF92E4-0511-4dda-A4CA-BADC763D26EC}", m_sInstanceName),
							new StateInitialData(StateEnum.Order_ENUM_PROCESSORDER_PACKED.ordinal(), StateEnum.NOSTATE.ordinal(), 0, false, "Order_ProcessOrder_Packed", "{E8BF119A-A566-46c8-88AC-33A8B6AB158F}", m_sInstanceName)
						};
		
					m_StateMachineImpl.CreateStateObjects(initialDataArray, nArrayCount, submachineState);
				}
				for(int i = 0; i < nEntryCount; i++)
				{
					switch(entryArray[i])
					{
					case Order_ENUM_PROCESSORDER_INITIAL_2612_2612:
						TransitionProc(TransitionEnum.Order_ENUM_INITIAL_2612_2612__TO__NEW_1584, signal, submachineState);
						break;
					}
				}
				if(submachineState.IsActiveState())
					m_StateMachineImpl.deferInternalEvent(EventProxy.EventEnum.COMPLETION, null, submachineState);
				break;
		}
	
	}
	
	public void runStateMachine(StateMachineEnum statemachine)
	{
		if (m_StateMachineImpl == null)
			return;
		
		List<StateData> activeStateArray = new ArrayList<StateData>();
		if(m_StateMachineImpl.GetCurrentStates(activeStateArray) > 0)
			return;
		switch (statemachine) 
		{
			case Order_ENUM_PROCESSORDER:
				{
					final int nArrayCount = 1;
					EntryEnum[] entryArray = {EntryEnum.Order_ENUM_PROCESSORDER_INITIAL_2612_2612};
					runStateMachine(statemachine, null, null, entryArray, nArrayCount);	//submachineState is NULL if run standalone statemachine 
				}
				break;
		}
	}
	
	public void runStateMachines()
	{
		runStateMachine(StateMachineEnum.Order_ENUM_PROCESSORDER);	
	}


	/* End - EA generated code for StateMachine */
}//end Order